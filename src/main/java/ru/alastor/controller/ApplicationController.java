package ru.alastor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alastor.dao.*;
import ru.alastor.domain.*;
import ru.alastor.domain.builder.ApplicationBuilder;
import ru.alastor.service.AuthTokenService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 23.09.17.
 *
 * @author Maxim Goncharov
 */

@RestController
@CrossOrigin
public class ApplicationController {

    private final ApplicationDao applicationDao;
    private final UserDao userDao;
    private final ApplicationHistoryDao applicationHistoryDao;
    private final AuthTokenService authTokenService;
    private final ApplicationSignDao applicationSignDao;
    private final RoleUserDao roleUserDao;
    private final Map<String, Integer> mapSortingByOrder;


    @Autowired
    public ApplicationController(ApplicationDao applicationDao, UserDao userDao, AuthTokenService authTokenService, ApplicationHistoryDao applicationHistoryDao, ApplicationSignDao applicationSignDao, RoleUserDao roleUserDao) {
        this.applicationDao = applicationDao;
        this.authTokenService = authTokenService;
        this.userDao = userDao;
        this.applicationHistoryDao = applicationHistoryDao;
        this.applicationSignDao = applicationSignDao;
        this.roleUserDao = roleUserDao;
        mapSortingByOrder = new HashMap<>();
        mapSortingByOrder.put("НИЗКИЙ", 1);
        mapSortingByOrder.put("СРЕДНИЙ", 2);
        mapSortingByOrder.put("ВЫСОКИЙ", 3);
    }

    @RequestMapping(path = "/applications", method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestParam("token") String token,
                                 @RequestParam("userLogin") String userLogin,
                                 @RequestParam(name = "sortedByOrder", required = false) String solveSorted,
                                 @RequestParam(name = "searchWord", required = false) String searchWord
                                 ) {

        if (authTokenService.getExistAuthToken(userLogin, token) == null) {
            return new ResponseEntity<>("error token or login", HttpStatus.ACCEPTED);
        }

        List<ApplicationSign> applicationSigns = applicationSignDao.getApplicationSignByUserRole(userDao.findByLogin(userLogin).getRole());
        List<Application> resp = new ArrayList<>();
        for (ApplicationSign aps : applicationSigns) {
            resp.add(applicationDao.findOne(aps.getApplicationId()));
        }


        if(solveSorted != null && !solveSorted.equals("")) {
            if (solveSorted.equals("UPDOWN")) {
                resp.sort((a, b) -> {
                    int aRating = mapSortingByOrder.get(a.getOrder());
                    int bRating = mapSortingByOrder.get(b.getOrder());
                    if (aRating > bRating)  {
                        return 1;
                    } else if (aRating < bRating) {
                        return -1;
                    } else {
                        return 0;
                    }
                });
            } else {
                resp.sort((a, b) -> {
                    int aRating = mapSortingByOrder.get(a.getOrder());
                    int bRating = mapSortingByOrder.get(b.getOrder());
                    if (aRating > bRating) {
                        return -1;
                    } else if (aRating < bRating) {
                        return 1;
                    }
                    return 0;
                });
            }
        }

        if (searchWord != null && !searchWord.equals("")) {
            resp = resp.stream().filter(a -> a.getName().contains(searchWord)).collect(Collectors.toList());
        }

        Collections.reverse(resp);

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @RequestMapping(path = "/applications/history", method = RequestMethod.GET)
    public ResponseEntity getAllHistory(@RequestParam("token") String token,
                                 @RequestParam("userLogin") String userLogin) {

        Token tokenOwner = authTokenService.getExistAuthToken(userLogin, token);

        if (tokenOwner == null) {
            return new ResponseEntity<>("error token or login", HttpStatus.ACCEPTED);
        }

        List<ApplicationHistory> applicationHistories = applicationHistoryDao.getApplicationHistoriesByUserSignerId(tokenOwner.getOwner().getId());
        Collections.reverse(applicationHistories);

        return new ResponseEntity<>(applicationHistories ,HttpStatus.OK);
    }



    @RequestMapping(path = "/applications/{appId}", method = RequestMethod.GET)
    public Application getAppById(@PathVariable(name = "appId") Long appId) throws JsonProcessingException {
        return applicationDao.findOne(appId);
    }

    @RequestMapping(path = "/applications/sign", method = RequestMethod.POST)
    public ResponseEntity signApp(@RequestParam("token") String token,
                                  @RequestParam("userLogin") String userLogin,
                                  @RequestParam("idApp") String idApp) throws JsonProcessingException {
        Token trueToken = authTokenService.getExistAuthToken(userLogin, token);

        if (trueToken == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        User owner = trueToken.getOwner();
        Application application = applicationDao.findOne(Long.valueOf(idApp));
        List<ApplicationSign> listAppSign = applicationSignDao.getApplicationSignByApplicationId(application.getId());
        System.out.println(listAppSign.size());
        boolean deleted = false;
        for(ApplicationSign applicationSign : listAppSign) {
            if (applicationSign.getUserRole().equals(owner.getRole())) {
                applicationSignDao.delete(applicationSign.getId());
                deleted = true;
                break;
            }
        }
        if (deleted) {
            if (applicationSignDao.getApplicationSignByApplicationId(application.getId()).size() == 0) {
                applicationHistoryDao.save(new ApplicationHistory(
                        application.getName(),
                        application.getInfo(),
                        application.getOrder(),
                        application.getUserId(),
                        application.getDateReview(),
                        owner.getId()
                ));
                applicationDao.delete(application.getId());
                return new ResponseEntity<>("task is done", HttpStatus.OK);

            }
            return new ResponseEntity<>("task is sign", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("you role is not corrected", HttpStatus.OK);
        }
    }


    @RequestMapping(path = "/new/application", method = RequestMethod.POST)
    public ResponseEntity newApplication(@RequestParam("name") String name,
                               @RequestParam("info") String info,
                               @RequestParam("order") String order,
                               @RequestParam("date") String date,
                               @RequestParam("userRoles") Integer[] userRoles,
                               @RequestParam("token") String token,
                               @RequestParam("userLogin") String userLogin
                               ) {
        if (authTokenService.getExistAuthToken(userLogin, token) == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = ApplicationBuilder.getInstance()
                .createApplication()
                .setName(name)
                .setInfo(info)
                .setDateReview(date)
                .setOrder(order)
                .setUserId(userDao.findByLogin(userLogin).getId())
                .getApplication();

        applicationDao.save(application);

        for (Integer userRole : userRoles) {
            applicationSignDao.save(new ApplicationSign(application.getId(),
                    roleUserDao.findOne((long)userRole)));
        }

        return new ResponseEntity<>(application.toString(),HttpStatus.OK);
    }


}

package com.mahull.admin.controllers;

import com.mahull.admin.util.Constants;
import com.mahull.model.model.CraftUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import static com.mahull.admin.util.Constants.ADD_NEW_USER_INFO_FORM;
import static com.mahull.admin.util.Constants.ADD_NEW_USER_INFO_SUCCESS;

/**
 * Created by Ugo on 30/03/2016.
 */
@Controller
@RequestMapping(value = "/account/*")
public class UserAccountController extends BaseController {

    /**
     *
     * @param model holds view object.
     * @return returns view name.
     */
    @RequestMapping(value = "new-user", method = RequestMethod.GET)
    public String show(Model model) {

        CraftUser craftUser = new CraftUser();
        model.addAttribute("craftUser", craftUser);
        return ADD_NEW_USER_INFO_FORM;
    }

    /**
     * will be used to add or update a user.
     * @param craftUser model object.
     * @param bindingResult bindingResults.
     * @return a string.
     */
    @RequestMapping( method = RequestMethod.POST)
    public String addNewUser(@ModelAttribute("craftUser") @Valid CraftUser craftUser, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ADD_NEW_USER_INFO_FORM;
        }

        final boolean isUserAlreadyExist = isUserNameAlreadyExist(craftUser.getUserName());

        if (!isUserAlreadyExist && !craftUser.isNew()) {
            craftUser.setPassword(passwordEncoder.encode(craftUser.getPassword()));
            craftUserRepository.save(craftUser);
        } else {
            craftUserRepository.updateUser(craftUser);
        }

        return ADD_NEW_USER_INFO_SUCCESS;
    }

    private boolean isUserNameAlreadyExist(String userName) {
        return craftUserRepository.getWithUserName(userName) == null ? false : true;
    }
}

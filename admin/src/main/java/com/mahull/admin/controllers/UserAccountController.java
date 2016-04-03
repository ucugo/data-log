package com.mahull.admin.controllers;

import com.mahull.model.model.CraftUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Ugo on 30/03/2016.
 */
@Controller
@RequestMapping(value = "/account/*")
public class UserAccountController extends BaseController {

//    @ModelAttribute("craftUser")
//    public CraftUser craftUser() {
//        return new CraftUser();
//    }

    @RequestMapping(value = "new-user", method = RequestMethod.GET)
    public String show(Model model) {
        model.addAttribute("craftUser", new CraftUser());
        return "/account/new-user :: info-form";
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
            return "/account/new-user :: info-form";
        }

        final boolean isUserAlreadyExist = isUserNameAlreadyExist(craftUser.getUserName());

        if (!isUserAlreadyExist && !craftUser.isNew()) {
            craftUser.setPassword(passwordEncoder.encode(craftUser.getPassword()));
            craftUserRepository.save(craftUser);
        } else {
            craftUserRepository.updateUser(craftUser);
        }

        return "/account/new-user :: info-success";
    }

    private boolean isUserNameAlreadyExist(String userName) {
        return craftUserRepository.getWithUserName(userName) == null ? false : true;
    }
}

package com.mahull.admin.security;

import com.mahull.model.model.CraftUser;
import com.mahull.model.repositories.CraftUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Ugo on 10/03/2016.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CraftUserRepository craftUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        CraftUser craftUser = craftUserRepository.getWithUserName(StringUtils.trimAllWhitespace(userName));
        return loadUser(craftUser);
    }

    private User loadUser(CraftUser craftUser) {

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(craftUser.getRole().name()));

        return new org.springframework.security.core.userdetails.User(craftUser.getUserName(), craftUser.getPassword(),
                true, true, true, true, authorities);
    }
}

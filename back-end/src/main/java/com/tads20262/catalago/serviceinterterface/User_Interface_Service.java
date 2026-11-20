package com.tads20262.catalago.serviceinterterface;

import com.tads20262.catalago.entity.User;

public interface User_Interface_Service {
    User getByEmail(String email);
    User save(User user);
    AccessToken autenticate (String email, String password);
}

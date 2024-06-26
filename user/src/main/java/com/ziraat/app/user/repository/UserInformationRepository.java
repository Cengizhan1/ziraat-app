package com.ziraat.app.user.repository;

import com.ziraat.app.user.model.User;
import com.ziraat.app.user.model.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {
    Boolean existsByUser(User user);
}

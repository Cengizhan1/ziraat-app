package com.ziraat.app.user.repository;

import com.ziraat.app.user.model.User;
import com.ziraat.app.user.model.UserContactInformation;
import com.ziraat.app.user.model.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactInformationRepository extends JpaRepository<UserContactInformation, Long> {

    Boolean existsByUser(User user);
}

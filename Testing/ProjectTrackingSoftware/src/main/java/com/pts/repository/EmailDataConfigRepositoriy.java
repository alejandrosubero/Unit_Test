package com.pts.repository;



import com.pts.entitys.EmailDataConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("emailDataConfig")
public interface EmailDataConfigRepositoriy extends CrudRepository<EmailDataConfig, Long> {

    public EmailDataConfig findByIdEmailConfig(Long id);
    public Optional<EmailDataConfig> findByPort(String port);
    public EmailDataConfig findByMailUsername(String username);
    public List<EmailDataConfig> findByMailUsernameContaining(String keyboard);

}

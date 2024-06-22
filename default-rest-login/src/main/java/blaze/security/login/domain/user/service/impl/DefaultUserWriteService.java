package blaze.security.login.domain.user.service.impl;

import blaze.security.login.domain.user.dto.item.UserRegisterItem;
import blaze.security.login.domain.user.repository.inf.UserWriteRepository;
import blaze.security.login.domain.user.service.inf.UserWriteService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userWriteService")
public class DefaultUserWriteService implements UserWriteService {
    private final UserWriteRepository userWriteRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultUserWriteService(UserWriteRepository userWriteRepository,
                                   PasswordEncoder passwordEncoder) {
        this.userWriteRepository = userWriteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public <T> T userRegister(UserRegisterItem item, Class<T> tClass) {
        String password = item.getPassword();
        item.encryptPassword(encodePassword(password));

        return userWriteRepository.userRegister(item, tClass);
    }

    /**
     * SCryptPasswordEncoder를 이용한 비밀번호 암호화 메서드
     *
     * @param password 비밀번호
     * @return 암호화 된 문자열
     */
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}

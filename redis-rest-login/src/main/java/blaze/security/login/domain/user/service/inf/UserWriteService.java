package blaze.security.login.domain.user.service.inf;

import blaze.security.login.domain.user.dto.item.UserRegisterItem;

public interface UserWriteService {

    <T> T userRegister(UserRegisterItem item, Class<T> tClass);
}

package ru.volodin.SarComp.service.autorization;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.volodin.SarComp.config.Constants;
import ru.volodin.SarComp.entity.authorization.Role;
import ru.volodin.SarComp.entity.authorization.User;
import ru.volodin.SarComp.repository.autorization.RoleRepository;
import ru.volodin.SarComp.repository.autorization.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@SuppressWarnings({"unused"})
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        if (StringUtils.isEmpty(user.getLogin()) || StringUtils.isEmpty(user.getPassword())) {
            throw new IllegalStateException("Введены неполные данные пользователя!");
        } else {
            if (userRepository.findByLogin(user.getLogin()).isPresent()) {
                throw new IllegalStateException("Пользователь с таким Email уже существует!");
            }

            Optional<Role> roleIfPresent = roleRepository.findByName(Constants.Roles.ROLE_USER_CODE);

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            //user.setRegDate(new Date());
            user.setRole(roleIfPresent
                    .orElseThrow(() -> new NoSuchElementException("Произошла ошибка, обратитесь к администратору")));
            return userRepository.save(user);
        }
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Пользователь не был найден в БД!"));
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}

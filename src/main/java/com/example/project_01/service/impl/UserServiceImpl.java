package com.example.project_01.service.impl;

import com.example.project_01.dto.UserDTO;
import com.example.project_01.entity.Account;
import com.example.project_01.entity.User;
import com.example.project_01.entity.enums.UserTypes;
import com.example.project_01.ex.UserNotFoundException;
import com.example.project_01.repo.UserRepo;
import com.example.project_01.service.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepo repo;
    private final Gson gson;

    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepo repo, Gson gson, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.gson = gson;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User adminByUsername = repo.findAdminByUsername(username);
            if (adminByUsername != null) {
                List<String> list = gson.fromJson(adminByUsername.getType(), new TypeToken<ArrayList<String>>() {
                }.getType());
                String[] objects = (String [])list.toArray();
                return org.springframework.security.core.userdetails.User.builder().username(
                        adminByUsername.getUsername()
                ).password(adminByUsername.getPassword()).roles(objects).build();
            }
            throw new UsernameNotFoundException("User not found");
        }catch (Exception e){
            throw new UsernameNotFoundException(e.getMessage(),e);
        }
    }

    @Override
    public UserDTO search(String email, String password) throws UserNotFoundException {

        try {
            User userByUsername = repo.findByEmail(email);

            if (passwordEncoder.matches(password, userByUsername.getPassword())) {
                ArrayList<String> list = gson.fromJson(userByUsername.getType(), new TypeToken<ArrayList<String>>() {});

                String[] objects = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    objects[i] = list.get(i);
                }

                return toDto(userByUsername);
            }
//            if (userByUsername != null && userByUsername.getPassword().equals(password) && userByUsername.getEmail().equals(email)) {
//                ArrayList<String> list = gson.fromJson(userByUsername.getType(), new TypeToken<ArrayList<String>>() {});
//
//                String[] objects = new String[list.size()];
//                for (int i = 0; i < list.size(); i++) {
//                    objects[i] = list.get(i);
//                }
////                return new UserDTO(userByUsername.getUsername(),userByUsername.getEmail(),userByUsername.getPassword(),objects);
//                return toDto(userByUsername);
//
//            }
            throw new UserNotFoundException("User not found");
        }catch (Exception e){
            throw new UserNotFoundException(e.getMessage(),e);
        }
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) throws UserNotFoundException {
        if (!repo.existsById(userDTO.getId())){
            User save = repo.save(toEntityConverter(userDTO));
            return toDto(save);
        }
        throw new UserNotFoundException("This ID is already exists");

    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) throws UserNotFoundException {
        if (repo.existsById(userDTO.getId())){
            User save = repo.save(toEntityConverter(userDTO));
            return toDto(save);
        }
        throw new UserNotFoundException("Dont have user with current ID");

    }

    @Override
    public UserDTO deleteUser(int id) throws UserNotFoundException {
        if (repo.existsById(id)){
            User user = repo.findById(id).get();
            repo.deleteById(id);
           return toDto(user);

        }
        throw new UserNotFoundException("Dont have user with current ID");
    }

    @Override
    public List<UserDTO> getAll() throws UserNotFoundException {
        List<User> all = (List<User>) repo.findAll();
        return toDtoList(all);
    }



    public List<UserDTO> findbyUsername(String name) throws UserNotFoundException {
        List<User> all = (List<User>) repo.findByUsernameLike("%" + name + "%");
        return toDtoList(all);
    }




    private User toEntityConverter(UserDTO userDTO){

        String[] type = userDTO.getType();
        String jsonArray = gson.toJson(type);

        return new User(userDTO.getId(),userDTO.getEmail(),userDTO.getUsername(),passwordEncoder.encode(userDTO.getPassword()),userDTO.getAddress(),userDTO.getNic(),userDTO.getDateofBirth(),
                userDTO.getTelNo(),jsonArray);
    }

    private UserDTO toDto(User user){
        ArrayList<String> list = gson.fromJson(user.getType(), new TypeToken<ArrayList<String>>() {});

        String[] objects = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            objects[i] = list.get(i);
        }

        return new UserDTO(user.getId(),user.getEmail(),user.getUsername(),user.getPassword(),
                user.getAddress(),user.getNic(),user.getDateofBirth(),user.getTelNo(),objects);

    }

    private List<UserDTO> toDtoList(List<User> users){
        List<UserDTO> dtoList=new ArrayList<>();
        for (User user:users){
            ArrayList<String> list = gson.fromJson(user.getType(), new TypeToken<ArrayList<String>>() {});

            String[] objects = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                objects[i] = list.get(i);
            }

            dtoList.add(new UserDTO(user.getId(),user.getEmail(),user.getUsername(),user.getPassword(),
                    user.getAddress(),user.getNic(),user.getDateofBirth(),user.getTelNo(),objects)) ;

        }
        return dtoList;
        }


}

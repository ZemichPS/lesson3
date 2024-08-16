package model;

import dao.FileUserRepository;
import dao.Repository;

import java.util.List;
import java.util.function.Function;

public class Currying {
    public static void main(String[] args) {
        Repository<Long, User> userRepository = new FileUserRepository();

        List<User> userList = userRepository.findAll();

    }
}

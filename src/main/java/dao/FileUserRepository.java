package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.User;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUserRepository implements Repository<Long, User> {

    @Override
    public User findById(Long id) {
        return findAll().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("User with id " + id + " not found"));
    }

    @Override
    public List<User> findAll() {
        try {
            return findUsersFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<User> findUsersFromFile() throws IOException {
        Path path = Paths.get("src/main/resources/initialData.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(path.toFile(), new TypeReference<List<User>>() {
        });
    }
}

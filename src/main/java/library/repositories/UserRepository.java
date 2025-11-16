package library.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import library.models.User;
import library.models.Student;
import library.models.Author;
import library.models.Librarian;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private static final String JSON_FILE_PATH = "src/main/resources/data/userCredentials.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Read all users from JSON file
     */
    public List<User> getAllUsers() throws IOException {
        List<User> users = new ArrayList<>();
        File file = new File(JSON_FILE_PATH);
        
        if (!file.exists()) {
            return users;
        }

        JsonNode rootNode = objectMapper.readTree(file);
        ArrayNode usersArray = (ArrayNode) rootNode.get("users");

        if (usersArray != null) {
            for (JsonNode userNode : usersArray) {
                User user = parseUserFromJson(userNode);
                if (user != null) {
                    users.add(user);
                }
            }
        }

        return users;
    }

    /**
     * Find user by username
     */
    public Optional<User> findByUsername(String username) throws IOException {
        List<User> users = getAllUsers();
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    /**
     * Check if username already exists
     */
    public boolean userExists(String username) throws IOException {
        return findByUsername(username).isPresent();
    }

    /**
     * Authenticate user (check username and password)
     */
    public Optional<User> authenticate(String username, String password) throws IOException {
        return findByUsername(username).stream()
                .filter(user -> user.getPassword() != null && user.getPassword().equals(password))
                .findFirst();
    }

    /**
     * Save a new user to JSON file
     */
    public boolean saveUser(String username, String password, String fullName, String role) throws IOException {
        // Check for duplicate username
        if (userExists(username)) {
            return false; // User already exists
        }

        File file = new File(JSON_FILE_PATH);
        JsonNode rootNode;

        // Read existing data or create new
        if (file.exists()) {
            rootNode = objectMapper.readTree(file);
        } else {
            rootNode = objectMapper.createObjectNode();
        }

        // Get or create users array
        ArrayNode usersArray = (ArrayNode) rootNode.get("users");
        if (usersArray == null) {
            usersArray = objectMapper.createArrayNode();
            ((ObjectNode) rootNode).set("users", usersArray);
        }

        // Get next userId
        int nextUserId = (int) usersArray.size() + 1;

        // Create new user object
        ObjectNode newUser = objectMapper.createObjectNode();
        newUser.put("userId", nextUserId);
        newUser.put("username", username);
        newUser.put("password", password);
        newUser.put("fullName", fullName);
        newUser.put("role", role);

        usersArray.add(newUser);

        // Write back to file
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, rootNode);
        return true;
    }

    /**
     * Parse a User from JSON node based on role
     */
    private User parseUserFromJson(JsonNode userNode) {
        int userId = userNode.get("userId").asInt();
        String username = userNode.get("username").asText();
        String password = userNode.get("password").asText();
        String fullName = userNode.get("fullName").asText();
        String role = userNode.get("role").asText();

        switch (role.toLowerCase()) {
            case "student":
            case "staff":
                return new Student(userId, username, password, fullName, userId);
            case "author":
                return new Author(userId, username, password, fullName, userId);
            case "librarian":
                return new Librarian(userId, username, password, fullName, userId);
            default:
                return null;
        }
    }

    /**
     * Update user password
     */
    public boolean updateUserPassword(String username, String newPassword) throws IOException {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            return false;
        }

        JsonNode rootNode = objectMapper.readTree(file);
        ArrayNode usersArray = (ArrayNode) rootNode.get("users");

        boolean found = false;
        for (int i = 0; i < usersArray.size(); i++) {
            JsonNode userNode = usersArray.get(i);
            if (userNode.get("username").asText().equals(username)) {
                ((ObjectNode) userNode).put("password", newPassword);
                found = true;
                break;
            }
        }

        if (found) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, rootNode);
        }

        return found;
    }
}

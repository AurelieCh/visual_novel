package core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Character;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class CharacterLoader {

    public static List<Character> loadCharacters(String path) {
        try (FileReader reader = new FileReader(path)) {
            Gson gson = new Gson();
            Type type = new TypeToken<CharacterWrapper>() {}.getType();
            CharacterWrapper data = gson.fromJson(reader, type);
            return data.characters;
        } catch (Exception e) {
            System.err.println("Error while loading characters: " + e.getMessage());
            return null;
        }
    }

    // Classe interne pour correspondre au JSON
    private static class CharacterWrapper {
        List<Character> characters;
    }
}

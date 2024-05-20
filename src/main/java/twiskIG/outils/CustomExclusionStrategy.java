package twiskIG.outils;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class CustomExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        // Ne pas exclure de champs spécifiques par cette stratégie
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        // Exclure uniquement la classe PointDeControle
        return clazz.equals(twiskIG.mondeIG.PointDeControleIG.class);
    }
}
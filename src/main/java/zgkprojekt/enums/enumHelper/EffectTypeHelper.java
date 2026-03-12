package zgkprojekt.enums.enumHelper;

import zgkprojekt.enums.EffectType;

import java.util.Arrays;
import java.util.List;

public class EffectTypeHelper {

        public static EffectType getRandomEffectType()
        {
            List<EffectType> result = Arrays.asList(EffectType.values());

            return result.get((int) (Math.random() *  result.size()));
        }
    }

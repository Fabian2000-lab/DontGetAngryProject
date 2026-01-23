package zgkprojekt.enums.enumHelper;

import zgkprojekt.enums.EventType;

import java.util.Arrays;
import java.util.List;

public class EventTypeHelper
{
    public static EventType getRandomEventType()
        {
            List<EventType> result = Arrays.asList(EventType.values());

            return result.get((int) (Math.random() *  result.size()));
        }
}

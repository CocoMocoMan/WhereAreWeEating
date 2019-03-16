package alexa;

import alexa.handlers.DistanceIntentHandler;
import alexa.handlers.LaunchRequestHandler;
import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;

public class WaweStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                    new LaunchRequestHandler(),
                    new DistanceIntentHandler()
                )
                .build();
    }

    public WaweStreamHandler() {
        super(getSkill());
    }

}
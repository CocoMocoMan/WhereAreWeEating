package main.java.alexa;

import main.java.alexa.handlers.DistanceIntentHandler;
import main.java.alexa.handlers.LaunchRequestHandler;
import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;

public class WaweStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .withSkillId("amzn1.ask.skill.53154342-9789-43a2-bd4d-81a251dee4f3")
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
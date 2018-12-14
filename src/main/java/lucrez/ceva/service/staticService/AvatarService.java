package lucrez.ceva.service.staticService;

import lucrez.ceva.model.enums.Gender;

public class AvatarService {
    private static final String DefaultManAvatar = "/unauthenticated/man-default.jpg";
    private static final String DefaultWomanAvatar = "/unauthenticated/woman-default.jpg";
//    private static final String DefaultUnspecifiedAvatar = "/unauthenticated/woman-default.jpg";

    public static String getDefaultAvatar() {
        return getDefaultAvatar(Gender.Male);
    }

    @SuppressWarnings("WeakerAccess")
    public static String getDefaultAvatar(Gender gender) {
        switch (gender) {
            case Female:
                return DefaultWomanAvatar;
            case Male:
            default:
                return DefaultManAvatar;
        }
    }
}

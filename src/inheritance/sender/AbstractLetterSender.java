package inheritance.sender;

import java.time.LocalTime;

public abstract class AbstractLetterSender {
    protected final LocalTime currentTime;

    public abstract void sendLetter();
    public AbstractLetterSender(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    protected String getTimeOfDayString() {

        int hour = currentTime.getHour();

        if (hour > 5 && hour <= 11) {
            return "morning";
        } else if (hour > 11 && hour <= 17) {
            return "afternoon";
        } else if (hour > 17 && hour <= 20) {
            return "evening";
        } else {
            return "night";
        }
    }
}

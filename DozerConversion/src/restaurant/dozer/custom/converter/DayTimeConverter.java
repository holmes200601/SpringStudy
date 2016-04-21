package restaurant.dozer.custom.converter;

import org.dozer.DozerConverter;

import restaurant.common.bean.component.DayTime;

public class DayTimeConverter extends DozerConverter<String, DayTime> {

    public DayTimeConverter() {
        super(String.class, DayTime.class);
    }

    @Override
    public DayTime convertTo(String source, DayTime destination) {
        destination = new DayTime();

        if (source != null && source.matches("^\\d{2}:\\d{2}$")) {
            String[] hm = source.split(":");
            int hour = stringToTime(hm[0]);
            int mini = stringToTime(hm[1]);
            destination.setHour((hour >= 0 && hour <= 23) ? hour : 9);
            destination.setMinite((mini >= 0 && mini <= 59) ? mini : 0);
        } else {
            assert (false);
        }

        return destination;
    }

    @Override
    public String convertFrom(DayTime source, String destination) {
        destination = "09:00";

        if (source != null) {
            StringBuilder builder = new StringBuilder(timeToString(source.getHour()));
            builder.append(":").append(timeToString(source.getMinite()));
            destination = builder.toString();
        }

        return destination;
    }

    private int stringToTime(String timeStr) {
        int result = timeStr.charAt(0) == '0' ? Integer.valueOf(timeStr.charAt(1)) : Integer.valueOf(timeStr);

        return result;
    }

    private String timeToString(int time) {
        if (time < 0 || time > 59) {
            return "00";
        }

        return (time < 10) ? ("0" + time) : String.valueOf(time);
    }

}

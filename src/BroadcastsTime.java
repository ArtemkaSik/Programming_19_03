public class BroadcastsTime implements Comparable<BroadcastsTime>{
    private int hour;
    private int minutes;

    public BroadcastsTime(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public boolean after(BroadcastsTime t) {
        return (this.hour > t.hour) || (this.hour == t.hour && this.minutes > t.minutes);
    }

    public boolean before(BroadcastsTime t) {
        return (this.hour < t.hour) || (this.hour == t.hour && this.minutes < t.minutes);
    }

    public boolean between(BroadcastsTime t1, BroadcastsTime t2) {
        return (this.after(t1) || this.equals(t1)) && (this.before(t2) || this.equals(t2));
    }

    @Override
    public int compareTo(BroadcastsTime t) {
        if (this.hour == t.hour) {
            return Integer.compare(this.minutes, t.minutes);
        }
        return Integer.compare(this.hour, t.hour);
    }
}

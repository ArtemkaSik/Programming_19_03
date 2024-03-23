public class BroadcastsTime implements Comparable<BroadcastsTime>{
    private byte hour;
    private byte minutes;

    public BroadcastsTime(byte hour, byte minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    BroadcastsTime(String data) {
        this.hour = Byte.parseByte("" + data.charAt(0) + data.charAt(1));
        this.minutes = Byte.parseByte("" + data.charAt(3) + data.charAt(4));
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public boolean after(BroadcastsTime t) {
        return ((getHour() >= t.getHour()) && (getMinutes() > t.getMinutes()));
    }

    public boolean before(BroadcastsTime t) {
        return ((getHour() <= t.getHour()) && (getMinutes() < t.getMinutes()));
    }

    boolean between(BroadcastsTime t1, BroadcastsTime t2) {
        if ( (getHour() < t2.getHour()) && (getHour() > t1.getMinutes()))
            return true;
        else if ((getHour() > t2.getHour()) && (getHour() < t1.getHour()))
            return false;
        else {
            return ( (getMinutes() < t2.getMinutes()) && (getMinutes() > t1.getMinutes()));
        }
    }

    @Override
    public boolean equals(Object obj) {
        BroadcastsTime bt = (BroadcastsTime) obj;
        return ((bt.getHour() == getHour()) && (bt.getMinutes() == getMinutes()));
    }

    @Override
    public int compareTo(BroadcastsTime t) {
        if (getHour() != t.getHour())
            return getHour() - t.getHour();
        else
            return getMinutes() - t.getMinutes();
    }

    @Override
    public String toString(){
        return "" + hour + ":" + minutes;
    }
}

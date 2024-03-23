class Program {
    private String channel;
    private BroadcastsTime time;
    private String name;

    public Program(String channel, BroadcastsTime time, String name) {
        this.channel = channel;
        this.time = time;
        this.name = name;
    }

    @Override
    public String toString() {
        return "" + channel + " " + time + " " + name;
    }

    public BroadcastsTime getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getChannel() {
        return channel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(BroadcastsTime time) {
        this.time = time;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
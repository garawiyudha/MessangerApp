package id.gara.MessangerApp;

public class Message {
    private String text;
    private MemberData data;
    private boolean belongsToCurrentUser;

    public Message(){
    }

    public Message(String text, MemberData data, Boolean belongsToCurrentUser){
        this.text = text;
        this.data = data;
        this.belongsToCurrentUser = belongsToCurrentUser;
    }

    public String getText(){
        return text;
    }

    public MemberData getData(){
        return data;
    }

    public boolean isBelongsToCurrentUser(){
        return belongsToCurrentUser;
    }
}

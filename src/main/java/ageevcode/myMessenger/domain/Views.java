package ageevcode.myMessenger.domain;

public final class Views {
    public interface Id{}
    public interface IdName extends Id{}
    public interface FullComment extends IdName{}
    public interface FullPost extends IdName{}
    public interface FullMessage extends IdName{}
    public interface WithoutPassword extends IdName{}
    public interface Password extends WithoutPassword{}

    /*public interface FullProfile extends WithoutPassword{}*/
}

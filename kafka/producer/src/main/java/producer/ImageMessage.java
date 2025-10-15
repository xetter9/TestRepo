package producer;

public class ImageMessage {
    private String filename;
    private byte[] data;
    
    public ImageMessage(String filename, byte[] data){
        this.filename=filename;
        this.data=data;
    }

    public ImageMessage(){

    }
    public void setFilename(String filename){
        this.filename=filename;
    }
    public void setData(byte[] data){
        this.data=data;
    }
    public String getFilename(){
        return this.filename;
    }
    public byte[] getData(){
        return this.data;
    }
}

public class Singleton
{ 
    private static Singleton instance = null; 
    Store s;
    
    private Singleton(Store s) 
    { 
        this.s = s; 
    }
    
    public static Singleton getInstance(Store s) 
    { 
        if (instance == null) 
        	instance = new Singleton(s); 
        return instance; 
    } 
} 
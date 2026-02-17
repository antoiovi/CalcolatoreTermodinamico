package com.antoiovi.logging;

public interface IOutput {

	public void setLevel(IOutput.Level level);
	
	public void logError(Object msg);
 
	public void logWarning(Object msg);
	 
	public void logInfo(Object msg) ;

	public void logDebug(Object msg);
	 
	public void logTrace(Object msg) ;
	
	public void clear();
	public void setMessage(Object msg);
	public void appendMessage(Object msg);
	
	public enum Level{
		     NOOUT ,  ERROR ,WARNING,INFO,DEBUG,TRACE
		 
	}
 
}

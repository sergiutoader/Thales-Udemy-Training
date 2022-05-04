package unittestingframework;

import java.lang.reflect.Method;

public class SimpleUnitTester {
    
    @SuppressWarnings("deprecation")
	public int execute(Class clazz) throws Exception {
        int failedCount = 0;
        
        // your code
        Object newInstance = clazz.newInstance();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
        	String methodName = method.getName();
        	Class returnType = method.getReturnType();
        	
        	if (methodName.startsWith("test") && returnType.equals(boolean.class)) {
        		boolean result = (boolean)method.invoke(newInstance);
        		if (!result) {
        			failedCount++;
        		}
        	}
        }
        
        return failedCount;
    }
    
}
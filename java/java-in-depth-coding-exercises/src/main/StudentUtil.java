 package main;

public class StudentUtil {
    
    static double getPoints(char grade) {
        double points = 0.0;
        
        switch (grade) {
            case 'A':
                points = 4.0;
                break;
            case 'B':
                points = 3.0;
                break;
            case 'C':
                points = 2.0;
                break;
        }
        
        return points;
    }

    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) {
        int studentCount = studentsGrades.length;
        double [] gpaValues = new double[studentCount];
        
        for(int i = 0; i < studentCount; i++) {
            for(char grade : studentsGrades[i]) {
                gpaValues[i] += getPoints(grade);
            }
            gpaValues[i] /= studentsGrades[i].length;
        }
        
        return gpaValues;
    }
    
    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {
        // perform parameter validation. Return null if passed parameters are not valid
        if (lower > higher || lower < 0 || higher < 0 || studentIdList == null || studentsGrades == null) {
        	return null;
        }
        
        int len = 0;
        int[] tempResult = new int[studentIdList.length];
    	
        // invoke calculateGPA
        double[] gpas = calculateGPA(studentIdList, studentsGrades);
        
        for(int i = 0; i < gpas.length; i++) {
        	if (lower <= gpas[i] && gpas[i] <= higher) {
        		tempResult[len++] = studentIdList[i];
        	}
        }
        
        // construct the result array and return it. You would need an extra for loop to compute the size of the resulting array
        int[] result = new int[len];
        for(int i = 0; i < len; i++) {
        	result[i] = tempResult[i];
        }
        
        return result;
    }
}
package algo0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16935_배열돌리기3 {

	static int N,M,R;
	static int[][] arr;
	static int curN, curM, arrSize;
	static int[][] halfArr1;
	static int[][] halfArr2;
	static int[][] halfArr3;
	static int[][] halfArr4;
		
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		st=new StringTokenizer(in.readLine(), " ");
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		
		curN=N; curM=M;
		
		if(N>=M) {
			arrSize=N;
		} else {
			arrSize=M;
		}
		
		arr=new int[arrSize][arrSize];
		
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		st=new StringTokenizer(in.readLine()," ");
		
		for (int i = 0; i < R; i++) {
			switch (Integer.parseInt(st.nextToken())) {
			case 1:
				upDown();
				break;
			case 2:
				leftRight();
				break;
			case 3:
				rotateRight();
				break;
			case 4:
				rotateLeft();
				break;
			case 5:
				changeCircle();
				break;
			case 6:
				changeReverse();
				break;
			default:
				break;
			}
		}

		for (int i = 0; i < curN; i++) {
			for (int j = 0; j < curM; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static void changeReverse() {
		int halfN=curN/2;
		int halfM=curM/2;
		
		createHalfArr(halfN, halfM);
		for (int i = 0; i < halfN; i++) {
			for (int j = 0; j < halfM; j++) {
				arr[i+halfN][j]=halfArr1[i][j];
				arr[i][j]=halfArr2[i][j];
				arr[i][j+halfM]=halfArr3[i][j];
				arr[i+halfN][j+halfM]=halfArr4[i][j];
			}
		}
	}

	private static void changeCircle() {
		int halfN=curN/2;
		int halfM=curM/2;
		
		createHalfArr(halfN, halfM);
		for (int i = 0; i < halfN; i++) {
			for (int j = 0; j < halfM; j++) {
				arr[i][j+halfM]=halfArr1[i][j];
				arr[i+halfN][j+halfM]=halfArr2[i][j];
				arr[i+halfN][j]=halfArr3[i][j];
				arr[i][j]=halfArr4[i][j];
			}
		}
	}

	private static void createHalfArr(int halfN, int halfM) {
		halfArr1 = new int[halfN][halfM];
		halfArr2 = new int[halfN][halfM];
		halfArr3 = new int[halfN][halfM];
		halfArr4 = new int[halfN][halfM];
 		
		for (int i = 0; i < halfN; i++) {
			halfArr1[i]=Arrays.copyOfRange(arr[i], 0, halfM);
			halfArr2[i]=Arrays.copyOfRange(arr[i], halfM, curM);
			halfArr3[i]=Arrays.copyOfRange(arr[i+halfN], halfM, curM);
			halfArr4[i]=Arrays.copyOfRange(arr[i+halfN], 0, halfM);
		}
	}

	private static void rotateLeft() {
		int[][] temp = new int[arrSize][arrSize];

		for (int i = 0; i < curM; i++) {
			for (int j = 0; j < curN; j++) {
				temp[i][j] = arr[j][curM-1-i];
			}
		}

		int tempCur=curM;
		curM=curN;
		curN=tempCur;

		for (int i = 0; i < curN; i++) {
			arr[i] = temp[i].clone();
		}
	}

	private static void rotateRight() {
		int[][] temp = new int[arrSize][arrSize];

		for (int i = 0; i < curM; i++) {
			for (int j = 0; j < curN; j++) {
				temp[i][j] = arr[curN-1-j][i];
			}
		}

		int tempCur=curM;
		curM=curN;
		curN=tempCur;

		for (int i = 0; i < curN; i++) {
			arr[i] = temp[i].clone();
		}
	}

	private static void leftRight() {
		int[][] temp = new int[arrSize][arrSize];

		for (int i = 0; i < curN; i++) {
			for (int j = 0; j < curM; j++) {
				temp[i][j] = arr[i][curM-1-j];
			}
		}

		for (int i = 0; i < curN; i++) {
			arr[i] = temp[i].clone();
		}
	}

	private static void upDown() {
		int[][] temp = new int[arrSize][arrSize];
		
		for (int i = 0; i < curN; i++) {
			for (int j = 0; j < curM; j++) {
				temp[i][j] = arr[curN-1-i][j];
			}
		}
		
		for (int i = 0; i < curN; i++) {
			arr[i] = temp[i].clone();
		}
	}
}

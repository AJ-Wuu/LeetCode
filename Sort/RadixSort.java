/**
 * @author AJWuu
 */

package radixSort;

import java.util.Arrays;

public class RadixSort {
	
	//Recursion
	//Sorting the last digits and moving left until the first digit is sorted
	//Time: O(nk)
	//Space: O(n+k)

	public static int getMax(int arr[], int n) {
		int max = arr[0];
		for (int i = 1; i < n; i++)
			if (arr[i] > max)
				max = arr[i];
		return max;
	}

	public static void countSort(int arr[], int n, int exp) {
		int output[] = new int[n];
		int count[] = new int[10];
		Arrays.fill(count, 0);

		for (int i=0; i<n; i++) {
			count[(arr[i] / exp) % 10]++;
		}

		for (int i=1; i<10; i++) {
			count[i] += count[i - 1];
		}

		for (int i=n-1; i>=0; i--) {
			output[count[(arr[i] / exp) % 10] - 1] = arr[i];
			count[(arr[i] / exp) % 10]--;
		}

		for (int i=0; i<n; i++) {
			arr[i] = output[i];
		}
	}

	public static void radixsort(int arr[], int n) {
		int m = getMax(arr, n);
		for (int exp=1; m/exp>0; exp*=10) {
			countSort(arr, n, exp);
		}
		
		for (int i=0; i<n; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	public static void main(String[] args) {
		int arr[] = { 170, 45, 75, 90, 802, 24, 2, 66 };
		int n = arr.length;
		radixsort(arr, n);
	}
	
}

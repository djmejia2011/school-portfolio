/*Iterative heap code was copied from geeksforgeeks.org*/

public class IterativeHeapSort {
    private static int count = 0;
    // function build Max Heap where value
    // of each child is always smaller
    // than value of their parent
    static void buildMaxHeap(int arr[], int n)
    {
        for (int i = 1; i < n; i++)
        {
            count++;
            // if child is bigger than parent
            if (arr[i] > arr[(i - 1) / 2])
            {
                int j = i;

                // swap child and parent until
                // parent is smaller
                while (arr[j] > arr[(j - 1) / 2])
                {
                    count++;
                    swap(arr, j, (j - 1) / 2);
                    j = (j - 1) / 2;
                }
            }
        }
    }

    static void heapSort(int arr[], int n)
    {
        buildMaxHeap(arr, n);

        for (int i = n - 1; i > 0; i--)
        {
            count++;
            // swap value of first indexed
            // with last indexed
            swap(arr, 0, i);

            // maintaining heap property
            // after each swapping
            int j = 0, index;

            do
            {
                count++;
                index = (2 * j + 1);

                // if left child is smaller than
                // right child point index variable
                // to right child
                if (index < (i - 1) && arr[index] < arr[index + 1])
                    index++;

                // if parent is smaller than child
                // then swapping parent with child
                // having higher value
                if (index < i && arr[j] < arr[index])
                    swap(arr, j, index);

                j = index;

            } while (index < i);
        }
    }

    public static void swap(int[] a, int i, int j) {
        count++;
        int temp = a[i];
        a[i]=a[j];
        a[j] = temp;
    }

    public static int getCount(){
        return count;
    }

    public static void setCountToZero(){
        count = 0;
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[])    {
        int n = arr.length;
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}

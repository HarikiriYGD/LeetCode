package LeetCode.SortAndSearch;


public class FirstBadVersion {
    /**
     * The isBadVersion API is defined in the parent class VersionControl.
     * boolean isBadVersion(int version);
     * <p>
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     * <p>
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     */
    public int firstBadVersion(int n) {
        return binarySearch(1, n);
    }

    public int binarySearch(int low, int high) {
        while (low < high) {
            // mid = (low + high) / 2 这样会导致溢出
            int mid = low + (high - low) / 2;
            if (isBadVersin(mid)) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return low;
    }

    public boolean isBadVersin(int n) {
        return 4 == n;
    }
}

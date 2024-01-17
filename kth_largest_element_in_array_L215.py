"""
Find kth largest element in a list using quick selection algorithm
"""
from math import ceil


# Worst case time complexity: O(n^2)
# Average case time complexity: O(n)


class Solution:
    def findKthLargest(self, nums: list[int], k: int) -> int:
        """
        Find kth largest element in a list using quick selection algorithm

        :param nums: nums is a list of integers
        :param k: int representing the kth largest element
        :precondition: nums must be a list of integers
        :precondition: k must a positive integer
        :return: int representing the kth largest element
        """
        pivot = nums[0]
        less_than_pivot = []
        same_as_pivot = []
        larger_than_pivot = []
        # O(n)
        for element in nums:
            if element > pivot:
                larger_than_pivot.append(element)
            elif element < pivot:
                less_than_pivot.append(element)
            else:
                same_as_pivot.append(element)

        num_elements_equal_or_larger_than_pivot = len(larger_than_pivot) + len(
            same_as_pivot
        )

        if k <= len(larger_than_pivot):
            return self.findKthLargest(larger_than_pivot, k)
        elif k > num_elements_equal_or_larger_than_pivot:
            return self.findKthLargest(
                less_than_pivot, k - num_elements_equal_or_larger_than_pivot
            )
        else:
            return pivot


"""
Find kth largest element in a list using quick selection and median of medians
"""

# Time complexity: O(n)
class Solution2:
    def findKthLargest(self, nums: list[int], k: int) -> int:
        """
        Find kth largest element in a list using quick selection and median of medians
        :param nums: a list of integers
        :param k: kth
        :return: kth largest element
        """
        pivot = self.medianOfMedians(nums)
        less_than_pivot = []
        same_as_pivot = []
        larger_than_pivot = []

        for element in nums:
            if element > pivot:
                larger_than_pivot.append(element)
            elif element < pivot:
                less_than_pivot.append(element)
            else:
                same_as_pivot.append(element)

        num_elements_equal_or_larger_than_pivot = len(larger_than_pivot) + len(same_as_pivot)

        if k <= len(larger_than_pivot):
            return self.findKthLargest(larger_than_pivot, k)
        elif k > num_elements_equal_or_larger_than_pivot:
            return self.findKthLargest(less_than_pivot, k - num_elements_equal_or_larger_than_pivot)
        else:
            return pivot

    def medianOfMedians(self, nums):
        """
        Find the median of medians of a list of integers
        :param nums: a list of integers
        :return: median of medians
        """
        medians = []
        if len(nums) <= 5:
            return sorted(nums)[len(nums) // 2]

        for i in range(0, len(nums) // 5):
            sliced_list = nums[5 * i:5 * i + 5]
            median = sorted(sliced_list)[len(sliced_list) // 2]
            medians.append(median)

        return self.findKthLargest(medians, ceil(len(medians) / 2))

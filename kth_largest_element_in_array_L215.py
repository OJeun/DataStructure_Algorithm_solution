"""
Find kth largest element in a list using quick selection algorithm
"""


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

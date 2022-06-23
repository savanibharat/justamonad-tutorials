package com.justamonad.tutorials.leetcode.array;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands200 {

	public static void main(String[] args) {
		char[][] grid = { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '1', '0', '0' },
				{ '0', '0', '0', '1', '1' } };

		NumberOfIslands200 ni = new NumberOfIslands200();
		int islands = ni.numIslandsIterativeWithVisitedArray(grid);
		System.out.println(islands);
	}

	private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public int numIslandsRecursiveWithoutVisitedArray(char[][] grid) {
		// null
		if (grid == null || grid.length == 0)
			return 0;

		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					count++;
					dfs(grid, i, j, DIRECTIONS);
				}
			}
		}

		return count;
	}

	private void dfs(char[][] grid, int i, int j, int[][] dirs) {
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0')
			return;

		// Mark the current territory as visited.
		grid[i][j] = '0';

		// Traverse to all 4 directions and do dfs.
		for (int[] dir : dirs) {
			dfs(grid, i + dir[0], j + dir[1], dirs);
		}

//		This condition works too		
//		if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && grid[i][j] == '1') {
//			// Mark the current territory as visited.
//			grid[i][j] = '0';
//
//			// Traverse to all 4 directions and do dfs.
//			for (int[] dir : dirs)
//				dfs(grid, i + dir[0], j + dir[1], dirs);
//		}

	}

	public int numIslandsRecursiveWithVisitedArray(char[][] grid) {
		// null
		if (grid == null || grid.length == 0)
			return 0;

		boolean[][] visited = new boolean[grid.length][grid[0].length];
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (!visited[i][j] && grid[i][j] == '1') {
					count++;
					dfs(grid, i, j, visited);
				}
			}
		}

		return count;
	}

	private void dfs(char[][] grid, int i, int j, boolean[][] visited) {
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0' || visited[i][j])
			return;

		// Mark the current territory as visited.
		visited[i][j] = true;

		// Traverse to all 4 directions and do dfs.
		for (int[] dir : DIRECTIONS) {
			dfs(grid, i + dir[0], j + dir[1], visited);
		}
	}

	public int numIslandsIterativeWithoutVisitedArray(char[][] grid) {
		// null
		if (grid == null || grid.length == 0)
			return 0;

		int count = 0;
		Queue<int[]> q = new LinkedList<>();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					count++;
					q.offer(new int[] { i, j });
					grid[i][j] = '0';
					while (!q.isEmpty()) {
						int[] curr = q.poll();
						for (int[] dir : DIRECTIONS) {
							int r = curr[0] + dir[0];
							int c = curr[1] + dir[1];
							if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == '1') {
								q.offer(new int[] { r, c });
								grid[r][c] = '0';
							}
//							This condition works too.
//							if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0')
//								continue;
//							q.offer(new int[] { r, c });
//							grid[r][c] = '0';
						}
					}
				}
			}
		}
		return count;
	}

	public int numIslandsIterativeWithVisitedArray(char[][] grid) {
		// null
		if (grid == null || grid.length == 0)
			return 0;

		boolean[][] visited = new boolean[grid.length][grid[0].length];
		int count = 0;
		Queue<int[]> q = new LinkedList<>();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (!visited[i][j] && grid[i][j] == '1') {
					count++;
					q.offer(new int[] { i, j });
					visited[i][j] = true;
					while (!q.isEmpty()) {
						int[] curr = q.poll();
						for (int[] dir : DIRECTIONS) {
							int r = curr[0] + dir[0];
							int c = curr[1] + dir[1];
							if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length
									&& grid[r][c] == '1' && !visited[r][c]) {
								q.offer(new int[] { r, c });
								grid[r][c] = '0';
							}
						}
					}
				}
			}
		}
		return count;
	}

}

package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Project;
import tn.esprit.spring.repository.ProjectRepository;
import tn.esprit.spring.serviceInterface.IProjectService;

@Service
@Slf4j
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	ProjectRepository projectRepo;

	@Override
	public List<Project> getAll() {
		try {
			List<Project> projects = (List<Project>) projectRepo.findAll();
			for (Project project : projects) {
				log.info(" Project : " + project);
			}
			return projects;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Project add(Project project) {
		try {
			return projectRepo.save(project);
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public Project update(Project project) {
		try {
			projectRepo.save(project);
			return project;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public void delete(Long projectid) {
		try {
			projectRepo.deleteById(projectid);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	@Override
	public Project getById(Long projectid) {
		try {
			Project project = projectRepo.findById(projectid).orElse(null);
			return project;
		}catch(Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}
	
	class ShortestPath {
	    // A utility function to find the vertex with minimum distance value,
	    // from the set of vertices not yet included in shortest path tree
	    static final int V = 9;
	    int minDistance(int dist[], Boolean sptSet[])
	    {
	        // Initialize min value
	        int min = Integer.MAX_VALUE, min_index = -1;
	 
	        for (int v = 0; v < V; v++)
	            if (sptSet[v] == false && dist[v] <= min) {
	                min = dist[v];
	                min_index = v;
	            }
	 
	        return min_index;
	    }
	 
	    // A utility function to print the constructed distance array
	    void printSolution(int dist[])
	    {
	        System.out.println("Vertex \t\t Distance from Source");
	        for (int i = 0; i < V; i++)
	            System.out.println(i + " \t\t " + dist[i]);
	    }
	 
	    // Function that implements Dijkstra's single source shortest path
	    // algorithm for a graph represented using adjacency matrix
	    // representation
	    /*
	    void dijkstra(int graph[][], int src)
	    {
	        int dist[] = new int[V]; // The output array. dist[i] will hold
	        // the shortest distance from src to i
	 
	        // sptSet[i] will true if vertex i is included in shortest
	        // path tree or shortest distance from src to i is finalized
	        Boolean sptSet[] = new Boolean[V];
	 
	        // Initialize all distances as INFINITE and stpSet[] as false
	        for (int i = 0; i < V; i++) {
	            dist[i] = Integer.MAX_VALUE;
	            sptSet[i] = false;
	        }
	 
	        // Distance of source vertex from itself is always 0
	        dist[src] = 0;
	 
	        // Find shortest path for all vertices
	        for (int count = 0; count < V - 1; count++) {
	            // Pick the minimum distance vertex from the set of vertices
	            // not yet processed. u is always equal to src in first
	            // iteration.
	            int u = minDistance(dist, sptSet);
	 
	            // Mark the picked vertex as processed
	            sptSet[u] = true;
	 
	            // Update dist value of the adjacent vertices of the
	            // picked vertex.
	            for (int v = 0; v < V; v++)
	 
	                // Update dist[v] only if is not in sptSet, there is an
	                // edge from u to v, and total weight of path from src to
	                // v through u is smaller than current value of dist[v]
	                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
	                    dist[v] = dist[u] + graph[u][v];
	        }
	 
	        // print the constructed distance array
	        printSolution(dist);
	    }
	    
	    
	    int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
            { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
            { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
            { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
            { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
            { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
            { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
            { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
            { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
ShortestPath t = new ShortestPath();*/

}}
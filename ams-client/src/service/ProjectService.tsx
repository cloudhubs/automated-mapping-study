import axios from 'axios';
import { Project } from '../model/Project';
import { Work } from '../model/Work';

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/'
  });

const ProjectService = {
    async getAllProjects() {
        const res = await axiosInstance.get<Project[]>("/project");
        return res.data;
    },

    async getProjectWorks(id: number) {
        const res = await axiosInstance.get<Work[]>(`/project/${id}/works`);
        return res.data;
    },

    async addQueryToProject(id: number, query: string) {
        const res = await axiosInstance.put<Work[]>(`/project/${id}/query`, {query: query, downloadPapers: true});
        return res.data;
    },

    async createProject(title: string) {
        const res = await axiosInstance.post<Project>(`/project/`, {title: title});
        return res.data;
    }
}

export default ProjectService;
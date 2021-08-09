export interface CourseActivity {
  id: number;
  description: string;
  sampleCode: string;
  hint?: string;
}

export interface CourseTopic {
  id: number;
  title: string;
  videoUrl: string;
  description: string;
  activities: CourseActivity[];
}


export interface CourseSection {
  id: number;
  title: string;
  orderNumber: number;
  topics: CourseTopic[];
}

export interface Course {
  id: number;
  title: string;
  imageUrl: string;
  sections: CourseSection[]
}

export interface CourseRequest {
  courseId: number;
  sectionId: number | null;
  topicId: number | null;
}

export interface CompiledCode {
  output: string,
  statusCode: number,
  memory: string,
  cpuTime: string
}

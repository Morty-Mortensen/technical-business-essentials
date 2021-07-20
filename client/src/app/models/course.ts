export interface CourseActivity {
  id: number;
  description: string;
  sampleCode: string;
}

export interface CourseTopic {
  id: number;
  title: string;
  videoUrl: string;
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
  sections: CourseSection[]
}

export interface CourseRequest {
  courseId: number;
  sectionId: number;
  topicId: number;
}

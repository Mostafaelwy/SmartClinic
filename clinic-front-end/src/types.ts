
export interface LoginRequest{
    email:String,
    password:String
}

export interface AuthenticatedUser{
    firstName:String,
    lastName:String,
    email:string,
    roles:[String]
}

export interface AuthorizedUser{
    token:String;
}


export interface Authority{
    authority:String;
}
// jwt.interfaces.ts
export interface JwtPayload {
  sub: string;         // Subject (usually user ID)
  exp: number;         // Expiration time
  iat: number;         // Issued at
  roles?: Authority[];     // User roles
  email?: string;       // User email
[key: string]: unknown;
  // Add other custom claims you expect
}

type KnownClaims = keyof JwtPayload;


export interface encodedToken<T = any> {
  header: {
    alg: string;
  };
  payload: T;
  signature: string;
}

export interface DoctorStatistics{
  totalPatient: number,
  totalPatientToday: number,
  totalAppointmentsToday: number
}


export interface ReservationFilter{
  status?: string;
  startTime?: string;
  endTime?: string;
  visitType?: string;
  patientName?: string;
  doctorName?: string;
};

export interface PagingFilter{
  pageNum?:number;
  pageSize?:number;
}

export interface ReservationItem {
  id: number;
  patientUserName: string;
  patientName: string;
  clinicName: string;
  status: 'ACCEPTED' | string;
  reservationDate: string;  // ISO date format: YYYY-MM-DD
  creationDate: string;     // ISO date format
  visitType: 'GENERAL' | string;
}

export interface SortInfo {
  empty: boolean;
  sorted: boolean;
  unsorted: boolean;
}

export interface Pageable {
  offset: number;
  sort: SortInfo;
  unpaged: boolean;
  paged: boolean;
  pageSize: number;
  pageNumber: number;
}


export interface PaginatedReservations {
  totalPages: number;
  totalElements: number;
  size: number;
  content: ReservationItem[];
  number: number;
  sort: SortInfo;
  first: boolean;
  last: boolean;
  numberOfElements: number;
  pageable: Pageable;
  empty: boolean;
}


export enum DateRangeEnum {
  TODAY = 'Today',
  LAST_7_DAYS = 'Last 7 Days',
  THIS_MONTH = 'This Month'
}


export enum ReservationStatus {
  ACCEPTED = 'ACCEPTED',
  REJECTED = 'REJECTED',
  PENDING = 'PENDING',
  COMPLETED = 'COMPLETED'
}


export interface Time {
  hour: number;
  minute: number;
  second: number;
  nano: number;
}

export interface TimeRange {
  startTime: Time;
  endTime: Time;
}

export interface Clinic {
  id: number;
  clinicName: string;
  logo: { url: string };
  workingHoursMap: Record<string, TimeRange>;
}


export interface DaySlot 
{ hour: number,
   minute: number }

   export type DayOfWeek =
  | 'MONDAY'
  | 'TUESDAY'
  | 'WEDNESDAY'
  | 'THURSDAY'
  | 'FRIDAY'
  | 'SATURDAY'
  | 'SUNDAY';

export type DaySlotMap = {
  [key in DayOfWeek]?: DaySlot[];
};

export interface SlotModel {
  startTime: string;       // Example: '04:00 PM'
  endTime: string;         // Example: '05:00 PM'
  interval: string;        // Example: '10 Minutes'
  duration: string;        // Example: '30 Minutes'
  space: number;           // 1–4
}


// slot.model.ts
export interface TimeObject {
  hour: number;
  minute: number;
  second: number;
  nano: number;
}

export interface SlotFormModel {
  startTimeStr: string;
  endTimeStr: string;
  durationMinutes: number;
  intervalMinutes: number;
}

export interface SlotDTO {
  startTime: string; // ISO time format "HH:mm"
  endTime: string;
  duration:string
  interval: string
}


export type WorkingHoursMap = Partial<Record<DayOfWeek, TimeObject[]>>;



// enums.ts
export enum Specialties {
  CARDIOLOGY = 'CARDIOLOGY',
  DERMATOLOGY = 'DERMATOLOGY',
  // ... Add more based on your backend enum values
}

export enum SpecialityService {
  CONSULTATION = 'CONSULTATION',
  SURGERY = 'SURGERY',
  // ... Add more based on your backend enum values
}


export interface SpecialityServiceDto {
  id: number;
  serviceType: SpecialityService;
  price: number;
  hint: string;
}



export interface SpecialityDto {
  id: number;
  speciality: Specialties;
  services: SpecialityServiceDto[];
}
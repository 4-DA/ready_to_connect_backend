from django.urls import path
from django.urls import path, include, re_path
from rest_framework import routers
from .views import MentorListCreateView, MentorRetrieveUpdateDestroyView, StudentListCreateView, StudentRetrieveUpdateDestroyView, GuardianListCreateView, GuardianRetrieveUpdateDestroyView, BusinessListCreateView, BusinessRetrieveUpdateDestroyView, SkillListCreateView, SkillRetrieveUpdateDestroyView, NotificationListCreateView, NotificationRetrieveUpdateDestroyView, InternshipListCreateView, InternshipRetrieveUpdateDestroyView


router = routers.DefaultRouter()


urlpatterns = [
    path('mentors/', MentorListCreateView.as_view(), name='mentor-list-create'),
    path('mentors/<uuid:pk>/', MentorRetrieveUpdateDestroyView.as_view(),
         name='mentor-detail'),

    path('students/', StudentListCreateView.as_view(), name='student-list-create'),
    path('students/<int:pk>/', StudentRetrieveUpdateDestroyView.as_view(),
         name='student-detail'),

    path('guardians/', GuardianListCreateView.as_view(),
         name='guardian-list-create'),
    path('guardians/<int:pk>/',
         GuardianRetrieveUpdateDestroyView.as_view(), name='guardian-detail'),

    path('businesses/', BusinessListCreateView.as_view(),
         name='business-list-create'),
    path('businesses/<int:pk>/',
         BusinessRetrieveUpdateDestroyView.as_view(), name='business-detail'),

    path('skill/', SkillListCreateView.as_view(),
         name='skill-list-create'),
    path('skill/<int:pk>/',
         SkillRetrieveUpdateDestroyView.as_view(), name='skill-detail'),


    path('skill/', NotificationListCreateView.as_view(),
         name='notification-list-create'),
    path('skill/<int:pk>/',
         NotificationRetrieveUpdateDestroyView.as_view(), name='notification-detail'),

    path('internship/', InternshipListCreateView.as_view(),
         name='internship-list-create'),
    path('internship/<int:pk>/',
         InternshipRetrieveUpdateDestroyView.as_view(), name='internship-detail'),




]

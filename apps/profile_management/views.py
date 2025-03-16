from rest_framework import viewsets, generics
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.exceptions import PermissionDenied
from rest_framework.permissions import IsAuthenticated


from apps.accounts.serializers import CustomUserReadSerializer
from .models import Mentor, Student, Guardian, Business, Skill, Notification, Internship
from .serializers import MentorSerializer, StudentSerializer, GuardianSerializer, BusinessSerializer, SkillSerializer, NotificationSerializer, InternshipSerializer


class MentorListCreateView(generics.ListCreateAPIView):
    queryset = Mentor.objects.all()
    serializer_class = MentorSerializer


class MentorRetrieveUpdateDestroyView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Mentor.objects.all()
    serializer_class = MentorSerializer


class StudentListCreateView(generics.ListCreateAPIView):
    queryset = Student.objects.all()
    serializer_class = StudentSerializer
    # Optional, if you want to restrict access to authenticated users
    # permission_classes = [IsAuthenticated]


class StudentRetrieveUpdateDestroyView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Student.objects.all()
    serializer_class = StudentSerializer
    # Optional, if you want to restrict access to authenticated users
    # permission_classes = [IsAuthenticated]


class GuardianListCreateView(generics.ListCreateAPIView):
    queryset = Guardian.objects.all()
    serializer_class = GuardianSerializer
    # Optional, if you want to restrict access to authenticated users
    # permission_classes = [IsAuthenticated]


class GuardianRetrieveUpdateDestroyView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Guardian.objects.all()
    serializer_class = GuardianSerializer
    # Optional, if you want to restrict access to authenticated users
    # permission_classes = [IsAuthenticated]


class BusinessListCreateView(generics.ListCreateAPIView):
    queryset = Business.objects.all()
    serializer_class = BusinessSerializer
    # Optional, if you want to restrict access to authenticated users
    # permission_classes = [IsAuthenticated]


class BusinessRetrieveUpdateDestroyView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Business.objects.all()
    serializer_class = BusinessSerializer
    # Optional, if you want to restrict access to authenticated users
    # permission_classes = [IsAuthenticated]


class SkillListCreateView(generics.ListCreateAPIView):
    queryset = Skill.objects.all()
    serializer_class = BusinessSerializer
    # Optional, if you want to restrict access to authenticated users
    # permission_classes = [IsAuthenticated]


class SkillRetrieveUpdateDestroyView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Skill.objects.all()
    serializer_class = SkillSerializer
    # Optional, if you want to restrict access to authenticated users
    # permission_classes = [IsAuthenticated]


class NotificationListCreateView(generics.ListCreateAPIView):
    queryset = Notification.objects.all()
    serializer_class = NotificationSerializer
    # Optional, if you want to restrict access to authenticated users
    # permission_classes = [IsAuthenticated]


class NotificationRetrieveUpdateDestroyView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Notification.objects.all()
    serializer_class = NotificationSerializer
    # Optional, if you want to restrict access to authenticated users
    # permission_classes = [IsAuthenticated]


class InternshipListCreateView(generics.ListCreateAPIView):
    queryset = Internship.objects.all()
    serializer_class = InternshipSerializer
    # Optional, if you want to restrict access to authenticated users
    # permission_classes = [IsAuthenticated]


class InternshipRetrieveUpdateDestroyView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Internship.objects.all()
    serializer_class = InternshipSerializer
    # Optional, if you want to restrict access to authenticated users
    # permission_classes = [IsAuthenticated]

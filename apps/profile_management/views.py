from rest_framework import viewsets, generics
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.exceptions import PermissionDenied
from rest_framework.permissions import IsAuthenticated


from apps.accounts.serializers import CustomUserReadSerializer
from models import Mentor, Student, Guardian, Business
from serializers import MentorSerializer, StudentSerializer, GuardianSerializer, BusinessSerializer


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
    permission_classes = [IsAuthenticated]


class StudentRetrieveUpdateDestroyView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Student.objects.all()
    serializer_class = StudentSerializer
    # Optional, if you want to restrict access to authenticated users
    permission_classes = [IsAuthenticated]


class GuardianListCreateView(generics.ListCreateAPIView):
    queryset = Guardian.objects.all()
    serializer_class = GuardianSerializer
    # Optional, if you want to restrict access to authenticated users
    permission_classes = [IsAuthenticated]


class GuardianRetrieveUpdateDestroyView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Guardian.objects.all()
    serializer_class = GuardianSerializer
    # Optional, if you want to restrict access to authenticated users
    permission_classes = [IsAuthenticated]


class BusinessListCreateView(generics.ListCreateAPIView):
    queryset = Business.objects.all()
    serializer_class = BusinessSerializer
    # Optional, if you want to restrict access to authenticated users
    permission_classes = [IsAuthenticated]


class BusinessRetrieveUpdateDestroyView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Business.objects.all()
    serializer_class = BusinessSerializer
    # Optional, if you want to restrict access to authenticated users
    permission_classes = [IsAuthenticated]

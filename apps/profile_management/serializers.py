from rest_framework import serializers
from .models import Mentor, Student, Guardian, Business, Skill, Notification, Internship
from apps.accounts.serializers import CustomUserReadSerializer


class MentorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Mentor
        fields = '__all__'  #
        read_only_fields = ['id']


class StudentSerializer(serializers.ModelSerializer):
    user = CustomUserReadSerializer()  # Serialize the related CustomUser (user)
    # Optional guardian (null allowed)
    guardian = CustomUserReadSerializer(allow_null=True)
    # Optional mentor (null allowed)
    mentor = CustomUserReadSerializer(allow_null=True)

    class Meta:
        model = Student
        fields = '__all__'  # Include all fields of the Student model
        # The 'user' field is generally the logged-in user and shouldn't be edited
        read_only_fields = ['user']


class GuardianSerializer(serializers.ModelSerializer):
    # Nested serializers for the `user` and `students` fields
    user = CustomUserReadSerializer()
    # `ManyToManyField` as a list of serialized users
    students = CustomUserReadSerializer(many=True)

    class Meta:
        model = Guardian
        fields = '__all__'  # Include all fields of the Guardian model
        # 'user' is typically the logged-in user, and shouldn't be editable
        read_only_fields = ['user']


class BusinessSerializer(serializers.ModelSerializer):
    # Nested serializer for the `user` field
    user = CustomUserReadSerializer()

    class Meta:
        model = Business
        fields = '__all__'  # Include all fields of the Business model
        # The 'user' field is usually the logged-in user and shouldn't be editable
        read_only_fields = ['user']


class SkillSerializer(serializers.ModelSerializer):
    # Serialize the related CustomUser (user)
    user = CustomUserReadSerializer()

    class Meta:
        model = Skill
        fields = '__all__'  # Include all fields of the Skill model
        # The 'user' field should generally be read-only
        read_only_fields = ['user']


class NotificationSerializer(serializers.ModelSerializer):
    # Serialize the related CustomUser (user)
    user = CustomUserReadSerializer()

    class Meta:
        model = Notification
        fields = '__all__'  # Include all fields of the Skill model
        # The 'user' field should generally be read-only
        read_only_fields = ['user']


class InternshipSerializer(serializers.ModelSerializer):
    # Serialize the related CustomUser (user)
    user = CustomUserReadSerializer()
    business = BusinessSerializer()
    # applicants = CustomUserReadSerializer()

    class Meta:
        model = Internship
        fields = '__all__'  # Include all fields of the Skill model
        # The 'user' field should generally be read-only
        read_only_fields = ['user', 'business', 'applicants']

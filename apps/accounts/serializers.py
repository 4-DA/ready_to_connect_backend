from rest_framework import serializers
from .models import CustomUser
import re
from dj_rest_auth.registration.serializers import RegisterSerializer
from dj_rest_auth.serializers import LoginSerializer
from rest_framework import serializers

from datetime import date


class CustomUserRegistrationSerializer(RegisterSerializer, serializers.ModelSerializer):
    USER_TYPES = ["Student", "Guardian", "Business", "Mentor"]

    full_name = serializers.CharField(required=True)
    date_of_birth = serializers.DateField(required=True)
    user_type = serializers.ChoiceField(choices=USER_TYPES, required=True)
    location = serializers.CharField(required=True)
    profile_picture = serializers.ImageField(required=True)
    streak = serializers.IntegerField(required=False, default=0)
    level = serializers.IntegerField(required=False, default=0)
    xp = serializers.IntegerField(required=False, default=0)
    badge = serializers.IntegerField(required=False, default=0)
    career_path = serializers.IntegerField(required=False, default=0)

    class Meta:
        model = CustomUser
        fields = ('email', 'password1', 'password2',
                  'full_name', 'date_of_birth', 'user_type', 'location', 'profile_picture', 'streak', 'level', 'xp', 'career_path', 'badge')

    def validate_date_of_birth(self, value):
        """ Ensure date of birth is not in the future. """
        if value > date.today():
            raise serializers.ValidationError(
                "Date of birth cannot be in the future.")
        return value

    def save(self, request):
        user = super().save(request)
        user.full_name = self.validated_data.get("full_name")
        user.date_of_birth = self.validated_data.get("date_of_birth")
        user.location = self.validated_data.get("location")
        user.profile_picture = self.validated_data.get("profile_picture")
        user.user_type = self.validated_data.get("user_type")
        user.level = self.validated_data.get("level")
        user.xp = self.validated_data.get("xp")
        user.streak = self.validated_data.get("streak")
        user.career_path = self.validated_data.get("career_path")
        user.badge = self.validated_data.get("badge")

        user.save()

        return user


class CustomUserReadSerializer(serializers.ModelSerializer):
    class Meta:
        model = CustomUser
        fields = ['pk', 'email', 'full_name', 'date_of_birth', 'created_on',
                  'location', 'profile_picture', 'user_type', 'streak', 'level', 'xp', 'career_path', 'badge'
                  ]


class CustomUserLoginSerializer(LoginSerializer, serializers.ModelSerializer):
    email = serializers.CharField(required=True)

    class Meta:
        model = CustomUser
        fields = ['email', 'password']


class NewEmailConfirmation(serializers.ModelSerializer):
    email = serializers.CharField(required=True)

    class Meta:
        model = CustomUser
        fields = ['email']

from django.db import models
from django.contrib.auth.models import AbstractUser
from django.utils.translation import gettext_lazy as _

from .managers import CustomUserManager


class CustomUser(AbstractUser):
    username = None
    email = models.EmailField(_('email address'), unique=True)
    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = []

    objects = CustomUserManager()

    # base fields
    full_name = models.CharField(max_length=200, null=True, blank=True)
    date_of_birth = models.DateField(null=True, blank=True)
    location = models.CharField(max_length=200, null=True, blank=True)
    created_on = models.DateTimeField(auto_now_add=True)
    modified_on = models.DateTimeField(auto_now=True)
    streak = models.IntegerField(default=0)
    profile_picture = models.ImageField(
        null=True, blank=True, upload_to="profile_pictures/",)
    user_type = models.CharField(max_length=10, null=True, blank=True)

    # student fields
    # skill_level = models.IntegerField(null=True, blank=True)
    # total_points = models.IntegerField(null=True, blank=True)
    # interest = models.CharField(null=True, blank=True)
    # skills = models.CharField(null=True, blank=True)
    # badge = models.CharField(null=True, blank=True)
    # educationLevel = models.CharField(null=True, blank=True)
    # guardianId     tradesman = models.ForeignKey(CustomUser, on_delete=models.CASCADE, related_name='tradesman', null=True, blank=True)
    # completedAssessments = models.CharField(null=True, blank=True)
    # recommendedPaths = models.CharField(null=True, blank=True)
    # appliedInternships = models.CharField(null=True, blank=True)
    # mentorId
    # aiConversationHistory = models.CharField(null=True, blank=True)

    def __str__(self):
        return self.email

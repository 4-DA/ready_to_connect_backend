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
    level = models.IntegerField(default=0)
    xp = models.IntegerField(default=0)
    badge = models.IntegerField(default=0)
    career_path = models.IntegerField(default=0)
    profile_picture = models.ImageField(
        null=True, blank=True, upload_to="profile_pictures/",)
    user_type = models.CharField(max_length=10, null=True, blank=True)

    def __str__(self):
        return self.email

from django.contrib import admin
from .models import Student, Guardian, Mentor, Business, Skill, Notification, Internship


admin.site.register(Student)
admin.site.register(Guardian)
admin.site.register(Mentor)
admin.site.register(Business)
admin.site.register(Skill)
admin.site.register(Notification)
admin.site.register(Internship)

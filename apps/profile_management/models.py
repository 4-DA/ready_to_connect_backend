from django.db import models
from apps.accounts.models import CustomUser
from django.contrib.postgres.fields import ArrayField
import uuid


class Student(models.Model):
    user = models.ForeignKey(
        CustomUser, related_name='student', on_delete=models.CASCADE)
    skill_level = models.IntegerField(null=True, blank=True, choices=[
                                      (i, str(i)) for i in range(1, 6)])  # 1-5
    total_points = models.IntegerField(default=0)
    # Alternative to ElementCollection
    interests = models.TextField(
        blank=True, help_text="Comma-separated list of interests")
    education_level = models.CharField(max_length=100, blank=True, null=True)
    # Reference to another User
    guardian = models.ForeignKey(
        CustomUser, on_delete=models.CASCADE, related_name='student_guardian', null=True, blank=True)
    # Reference to another User
    mentor = models.ForeignKey(
        CustomUser, on_delete=models.CASCADE, related_name='student_mentor', null=True, blank=True)


class Guardian(models.Model):
    # Primary key referencing a User (assumed as a string ID)
    # Adjust length as needed
    user = models.ForeignKey(
        CustomUser, on_delete=models.CASCADE, related_name='guardian')

    # List of student IDs stored as JSON
    # Stores a list of strings
    students = models.ManyToManyField(
        CustomUser, related_name='guardian_students')

    # Notification preferences as individual fields
    notification_email = models.BooleanField(default=False)
    notification_application = models.BooleanField(default=False)
    notification_weekly_updates = models.BooleanField(default=False)

    # List of approved company IDs stored as JSON
    approved_companies = models.JSONField(
        default=list, blank=True)  # Stores a list of strings


class Mentor(models.Model):
    user = models.ForeignKey(
        CustomUser, on_delete=models.CASCADE, related_name='mentor')
    name = models.CharField(max_length=255, null=False, blank=False)
    description = models.TextField(null=True, blank=True)
    avatar_url = models.ImageField(
        null=True, blank=True, upload_to="avatar_pictures/",)
    is_ai = models.BooleanField(default=False)
    industry = models.CharField(max_length=255, null=True, blank=True)
    student_count = models.PositiveIntegerField(default=0)
    welcome_message = models.TextField(null=True, blank=True)


class Business(models.Model):
    # Link to User model
    user = models.ForeignKey(
        CustomUser, on_delete=models.CASCADE, related_name='business')

    # Fields from Business entity
    company_name = models.CharField(max_length=255)
    industry = models.CharField(max_length=100)
    description = models.TextField(blank=True, null=True)
    website_url = models.URLField(blank=True, null=True)
    logo_url = models.URLField(blank=True, null=True)

    # Enum for VerificationStatus
    VERIFICATION_STATUS_CHOICES = (
        ('PENDING', 'Pending'),
        ('VERIFIED', 'Verified'),
        ('REJECTED', 'Rejected'),
    )
    verification_status = models.CharField(
        max_length=20,
        choices=VERIFICATION_STATUS_CHOICES,
        default='PENDING'
    )
    contact_email = models.EmailField(blank=True, null=True)
    contact_phone = models.CharField(max_length=20, blank=True, null=True)

    # Relationships
    target_skill_levels = ArrayField(
        models.IntegerField(),
        default=list,
        blank=True,
        help_text="List of target skill levels"
    )


# class Skill(models.Model):
#     id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
#     name = models.CharField(max_length=255, null=False, blank=False)
#     category = models.CharField(max_length=255, null=True, blank=True)
#     description = models.TextField(null=True, blank=True)
#     level = models.IntegerField(null=False, blank=False)  # 1-5
#     points = models.IntegerField(null=True, blank=True)
#     last_assessed = models.DateTimeField(null=True, blank=True)


# class Notification(models.Model):
#     # Enum equivalent using choices
#     NOTIFICATION_TYPES = (
#         ('ALERT', 'Alert'),
#         ('ACHIEVEMENT', 'Achievement'),
#         ('APPLICATION', 'Application'),
#         ('MESSAGE', 'Message'),
#         ('RECOMMENDATION', 'Recommendation'),
#     )

#     # Fields
#     id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
#     # Assuming user_id is a string reference
#     user_id = models.CharField(max_length=255, null=False, blank=False)
#     title = models.CharField(max_length=255, null=False, blank=False)
#     message = models.TextField(null=False, blank=False)
#     type = models.CharField(
#         max_length=20, choices=NOTIFICATION_TYPES, default='ALERT')
#     read = models.BooleanField(default=False)
#     created_at = models.DateTimeField(default=timezone.now)
#     action_link = models.URLField(null=True, blank=True)  # Nullable field
#     related_id = models.CharField(
#         max_length=255, null=True, blank=True)  # Nullable field


# class Internship(models.Model):
#     # Enum equivalent using choices
#     STATUS_CHOICES = (
#         ('DRAFT', 'Draft'),
#         ('ACTIVE', 'Active'),
#         ('FILLED', 'Filled'),
#         ('CLOSED', 'Closed'),
#     )

#     # Fields
#     id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
#     # Assuming string reference to Business
#     business_id = models.CharField(max_length=255, null=True, blank=True)
#     title = models.CharField(max_length=255)
#     description = models.TextField()
#     location = models.CharField(max_length=255)
#     is_remote = models.BooleanField(default=False)
#     start_date = models.DateField()
#     end_date = models.DateField()
#     application_deadline = models.DateField()
#     skill_level_required = models.IntegerField(
#         null=True, blank=True)  # 1-5, nullable
#     status = models.CharField(
#         max_length=20, choices=STATUS_CHOICES, default='DRAFT')
#     # Optional, using FloatField for Double
#     stipend = models.FloatField(null=True, blank=True)
#     hours_per_week = models.IntegerField(null=True, blank=True)
#     application_process = models.TextField(null=True, blank=True)
#     applicants = models.ManyToManyField(
#         'students.Student', related_name='internships', blank=True)


# class CareerPath(models.Model):
#     id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
#     title = models.CharField(max_length=255)
#     description = models.TextField()

#     # Using ArrayField for lists (requires PostgreSQL)
#     industries = ArrayField(models.CharField(max_length=255), default=list)

#     # required_skills is handled via the Skill model (OneToMany relationship)

#     recommended_courses = ArrayField(
#         models.CharField(max_length=255), default=list)
#     potential_jobs = ArrayField(models.CharField(max_length=255), default=list)
#     # Double in Java -> Float in Django
#     average_salary = models.FloatField(null=True, blank=True)
#     growth_outlook = models.CharField(max_length=255, null=True, blank=True)
#     popularity_score = models.FloatField(null=True, blank=True)
#     matching_internships = ArrayField(models.CharField(
#         max_length=255), default=list)  # Internship IDs as strings


# class Badge(models.Model):
#     id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
#     name = models.CharField(max_length=255, null=False, blank=False)
#     description = models.TextField(null=True, blank=True)
#     image_url = models.URLField(null=True, blank=True)
#     category = models.CharField(max_length=100, null=True, blank=True)
#     points_required = models.IntegerField(null=True, blank=True)
#     unlocked_at = models.DateTimeField(null=True, blank=True)


# class AssessmentResult(models.Model):
#     id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
#     student_id = models.CharField(
#         max_length=100, help_text="Reference to Student")
#     assessment_id = models.CharField(
#         max_length=100, help_text="Reference to AssessmentGame")
#     score = models.FloatField(null=True, blank=True)
#     completed_at = models.DateTimeField(null=True, blank=True)
#     skill_level_before = models.IntegerField(null=True, blank=True)
#     skill_level_after = models.IntegerField(null=True, blank=True)
#     points_earned = models.IntegerField(null=True, blank=True)
#     # Using JSONField for simplicity
#     badges_earned = models.JSONField(
#         default=list, help_text="List of Badge IDs")
#     feedback = models.TextField(null=True, blank=True)


# class Answer(models.Model):
#     assessment_result = models.ForeignKey(
#         AssessmentResult,
#         on_delete=models.CASCADE,
#         related_name='answers'
#     )
#     question_id = models.CharField(max_length=100)
#     answer = models.TextField()
#     is_correct = models.BooleanField(default=False)
#     points_earned = models.IntegerField(null=True, blank=True)


# class AssessmentGame(models.Model):
#     id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
#     title = models.CharField(max_length=255)
#     description = models.TextField(blank=True, null=True)
#     skill_category = models.CharField(
#         max_length=100, db_column="skill_category")
#     difficulty = models.IntegerField(choices=[(i, str(i)) for i in range(
#         1, 6)], help_text="Difficulty level from 1 to 5")
#     time_limit = models.IntegerField(
#         db_column="time_limit", help_text="Time limit in minutes")
#     points_available = models.IntegerField(db_column="points_available")
#     completion_badge_id = models.CharField(
#         max_length=100, db_column="completion_badge_id", blank=True, null=True, help_text="Reference to Badge")


# class Question(models.Model):
#     id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
#     assessment_game = models.ForeignKey(
#         AssessmentGame, on_delete=models.CASCADE, related_name="questions")
#     text = models.TextField()  # Assuming a basic question structure
#     correct_answer = models.CharField(
#         max_length=255, blank=True, null=True)  # Adjust based on your needs
#     points = models.IntegerField(default=0)


# # AR ROOM
# # ar_app/models.py

# # Vector3D as an embeddable component (modeled as a separate model or fields in parent models)

# class Vector3D(models.Model):
#     x = models.FloatField(null=True, blank=True)
#     y = models.FloatField(null=True, blank=True)
#     z = models.FloatField(null=True, blank=True)

#     class Meta:
#         abstract = True  # Makes this reusable without creating a separate table

#     def __str__(self):
#         return f"({self.x}, {self.y}, {self.z})"

# # ARRoom model


# class ARRoom(models.Model):
#     ROOM_TYPES = (
#         ('INTERVIEW', 'Interview'),
#         ('NETWORKING', 'Networking'),
#         ('SKILL_DEMO', 'Skill Demo'),
#     )

#     id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
#     name = models.CharField(max_length=255)
#     # description field is commented out in Java, so omitted here
#     type = models.CharField(max_length=20, choices=ROOM_TYPES)
#     background_url = models.URLField(blank=True, null=True)

#     # List<Integer> availableToLevels (using ArrayField or JSONField for simplicity)
#     available_to_levels = models.JSONField(
#         default=list)  # Stores list of integers

#     def __str__(self):
#         return self.name

# # ObjectModel model


# class ObjectModel(models.Model):
#     id = models.BigAutoField(primary_key=True)
#     ar_room = models.ForeignKey(
#         ARRoom, on_delete=models.CASCADE, related_name='object_models')
#     object_url = models.URLField()

#     # Embedded Vector3D fields for position, scale, and rotation
#     position_x = models.FloatField(null=True, blank=True)
#     position_y = models.FloatField(null=True, blank=True)
#     position_z = models.FloatField(null=True, blank=True)

#     scale_x = models.FloatField(null=True, blank=True)
#     scale_y = models.FloatField(null=True, blank=True)
#     scale_z = models.FloatField(null=True, blank=True)

#     rotation_x = models.FloatField(null=True, blank=True)
#     rotation_y = models.FloatField(null=True, blank=True)
#     rotation_z = models.FloatField(null=True, blank=True)

#     def __str__(self):
#         return f"ObjectModel {self.id} in {self.ar_room.name}"

# # InteractionPoint model


# class InteractionPoint(models.Model):
#     id = models.BigAutoField(primary_key=True)
#     ar_room = models.ForeignKey(
#         ARRoom, on_delete=models.CASCADE, related_name='interaction_points')

#     # Embedded Vector3D fields for position
#     position_x = models.FloatField(null=True, blank=True)
#     position_y = models.FloatField(null=True, blank=True)
#     position_z = models.FloatField(null=True, blank=True)

#     action = models.CharField(max_length=255)
#     trigger_event = models.CharField(max_length=255)

#     def __str__(self):
#         return f"InteractionPoint {self.id} in {self.ar_room.name}"

# # END OF AR ROOM


# class AIInteraction(models.Model):
#     # UUID field as the primary key, auto-generated
#     id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)

#     # Reference to student_id (assuming no ForeignKey relation specified in original)
#     # Adjust max_length as needed
#     student_id = models.CharField(max_length=255, blank=True, null=True)

#     # Timestamp with default value set to current time
#     timestamp = models.DateTimeField(auto_now_add=True)

#     # User query and AI response as text fields
#     user_query = models.TextField()
#     ai_response = models.TextField()

#     # List of related skill categories (using ArrayField for PostgreSQL)
#     # Note: Requires PostgreSQL. For SQLite/MySQL, see alternatives below.
#     related_skill_categories = ArrayField(
#         models.CharField(max_length=100),
#         default=list,
#         blank=True
#     )

#     # Points awarded as an integer, nullable
#     points_awarded = models.IntegerField(null=True, blank=True)

#     # List of resources generated (using ArrayField for PostgreSQL)
#     resources_generated = ArrayField(
#         models.CharField(max_length=255),
#         default=list,
#         blank=True
#     )

#     # Sentiment score as a float, nullable
#     sentiment_score = models.FloatField(null=True, blank=True)
